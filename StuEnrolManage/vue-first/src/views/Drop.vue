<template>
<div>
    <el-button type = "primary">wd</el-button>
         <p>{{ "Hello, this is a line of text." }}</p>
        <p>Welcome, Student ID: {{ studentData.id }}</p>

        <el-table :data="tableData" border stripe style="width: 100%">
  <el-table-column prop="idcoursetable" label="idcourseTable" width="180" />
  <el-table-column prop="courseid" label="courseID" width="180" />
  <el-table-column prop="coursename" label="courseName" width="180" />

  <el-table-column fixed="right" label="Operation" width="200"> 
      
      <template #default="scope">
        <el-button size = "mini" type="danger" @click="handleDrop(scope.row.idcoursetable)">Drop</el-button>
      </template>
    </el-table-column>


  </el-table>

</div>
</template>

<script>
import request from "@/utils/request";
export default{
   
    name: "DropPage",

    components: {
        
},


mounted() {
  try {
    const studentId = this.$route.params.studentId;
    // Simulate the process of asynchronous acquisition of student data
    this.getStudentDataById(studentId).then(studentData => {
      // Assign the obtained student data to studentData
      this.studentData = studentData;

      // Call the load method after obtaining the student data successfully
      this.load();
    }).catch(error => {
      console.error('Error fetching student data:', error);
    });
  } catch (error) {
    console.error('Error fetching student data:', error);
  }
},

data() {
  return {
    studentData: {},
    tableData: [],
  };
},

 created() {
       this.load()
 },

 methods: {

   handleDrop(idcoursetable){
    request.delete(`/register/${idcoursetable}`).then(res => {
       if(res.code==='0'){
           this.$message(
        {
          type: "success",
          message: "delete success"
        }
           )
      }
      else{
        this.$message(
        {
          type: "error",
          message: res.msg
        }
        )
      }
      this.load()
    })

           
   },
    // You need to replace the function that simulates asynchronous acquisition of student data with the actual data acquisition logic
    async getStudentDataById(studentId) {
      // This can be the actual data acquisition logic, such as requesting through an interface
      // Here, setTimeout is used to simulate asynchronous requests, and you need to replace them according to the actual situation
      return new Promise((resolve) => {
        setTimeout(() => {
          // What is returned here is the passed student ID and name
          resolve({ id: studentId });

          
            // Add more data as needed
    
        }, 1000); // Simulate a 1-second delay
      });
    },

   load() {
  // Check if this studentData exists and if it contains the id attribute
  if (this.studentData && this.studentData.id) {
    console.log(this.studentData.id);
    request.get("/register", {
      params: {
        coursestuid: this.studentData.id,
      },
    }).then(res => {
      console.log(res);
      this.tableData = res.data;
    }).catch(error => {
      console.error("Error loading data:", error);
    });
  } else {
    console.error("Invalid student data or missing student ID");
  }
}

 }

}
</script>
