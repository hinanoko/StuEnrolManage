<template>
    <div>
        <el-button type = "primary">wd</el-button>
         <p>{{ "Hello, this is a line of text." }}</p>
        <p>Welcome, Student ID: {{ studentData.id }}</p>

        <el-table :data="tableData" border stripe style="width: 100%">
  <el-table-column prop="idcourseinfo" label="idcourseInfo" width="180" />
  <el-table-column prop="courseid" label="courseID" width="180" />
  <el-table-column prop="coursename" label="courseName" width="180" />

  <el-table-column fixed="right" label="Operation" width="200"> 
      
      <template #default="scope">
        <el-button size = "mini" type="success" @click="handleEnrol(scope.row)">Enrol</el-button>
      </template>
    </el-table-column>


  </el-table>

    </div>
</template>

<script>
import request from "@/utils/request";
export default{
    name: "Enrol",

    components: {
        
    },

  

     async mounted() {
    try {
      const studentId = this.$route.params.studentId;
      // Simulate the process of asynchronous acquisition of student data
      const studentData = await this.getStudentDataById(studentId);

      // Assign the obtained student data to studentData
      this.studentData = studentData;
    } catch (error) {
      console.error('Error fetching student data:', error);
    }
  },


    data() {
  return {
    studentData: {},
    tableData: [],
    courseNum: 0,
  };
},

created() {
      
  const studentId = this.$route.params.studentId;
       this.load(studentId);
       this.getCourseNum();
 },


  methods: {
    // You need to replace the function that simulates asynchronous acquisition of student data with the actual data acquisition logic
    async getStudentDataById(studentId) {
      //This can be the actual data acquisition logic, such as requesting through an interface

//Here, setTimeout is used to simulate asynchronous requests, and you need to replace them according to the actual situation
      return new Promise((resolve) => {
        setTimeout(() => {
          //What is returned here is the passed student ID and name
          resolve({ id: studentId });

          
            //Add more data as needed
    
        }, 1000); //Simulate a 1-second delay
      });
    },

     handleEnrol(row) {
      // Handle edit action
      
      const courseInfo = {
    coursemark: 75,
    courseid: row.courseid,
    coursename: row.coursename,
    //Add properties for other courseInfo
  };
   let num = this.tableData.length;
   let courseNum = this.courseNum;
   console.log(num);
   console.log(courseNum);
   let newnum = courseNum - num;
if(newnum < 4){
      request
        .post("/register", {
          coursemark: 75,
         courseid: row.courseid,
           coursename: row.coursename,
          coursestuid: this.studentData.id,
        })
        .then((res) => {
            console.log(row.coursemark, row.courseid, row.coursename);
          console.log("Enrol data sent successfully:", res);
          const studentId = this.$route.params.studentId;
        this.load(studentId);
        })
        .catch((error) => {
          console.error("Error sending enrol data:", error);
        });

}
else{
   this.$message(
        {
          message: "you have got the maximum number of courses",
        }
   )
}

        
    },

    handleDelete(id) {
      // Handle delete action
    },

    tableRowClassName({ row, rowIndex }) {
      // Add row class logic if needed
      return '';
    },

 async getCourseNum() {
  try {
    //Using the await keyword to wait for the result of an asynchronous request
    const response = await request.get("/login");
    this.courseNum = response.data; //Assuming the data structure returned by the backend is {num: 10}
    console.log(this.courseNum);
  } catch (error) {
    console.error("Error fetching total courses:", error);
  }
},

    load(studentId){
    request.get(`/enrol/${studentId}`
    ).then(res => {
      
      console.log(res)
      this.tableData = res.data
    })

    
   }
  },

}
</script>
