import axios from 'axios'
import router from "@/router";

const request = axios.create({
    baseURL: "/api",
    timeout: 5000
})

// Request a whitelist. If the request is on the whitelist, it will not be intercepted for permission verification
const whiteUrls = ["/student/login", '/student/register']

// Request interceptor
// You can handle the request before sending it on your own
// For example, adding tokens uniformly and encrypting request parameters uniformly
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    // Retrieve cached user information from session storage
    let studentJson = sessionStorage.getItem("student")
    if (!whiteUrls.includes(config.url)) {  // Verify request whitelist
        if(!studentJson) {
           // router.push("/login")
        } else {
            let student = JSON.parse(studentJson);
            config.headers['token'] = student.token;  // Set request header
        }
    }
    return config
}, error => {
    return Promise.reject(error)
});

//Response interceptor
// Unified processing of results after interface response
request.interceptors.response.use(
    response => {
        let res = response.data;
        // If it is a returned file
        if (response.config.responseType === 'blob') {
            return res
        }
        // Compatible with string data returned by the server
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // vertify token
        if (res.code === '401') {
            console.error("token expired. login again")
            router.push("/login")
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request

