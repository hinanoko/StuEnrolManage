<template>
    <div>
        This is Admin
        <el-button @click="goToHome" type="primary">Back to Home</el-button>
        <br>
        <br>

  

     <el-input v-model="input" placeholder="Please input" :style="{ width: '450px' }" class="custom-input" />
      <el-button @click="handleSearch" type="primary" style="margin-left: 10px; margin-top: 20px">Search</el-button>
    <el-button @click="handleReset" type="danger" style="margin-left: 10px; margin-top: 20px">Reset</el-button>


  <el-table :data="tableData" :border="parentBorder" style="width: 100%">
    <el-table-column type="expand">
      <template #default="props">
        <div m="4">
          <p m="t-0 b-2">Password: {{ props.row.student.password }}</p>
          <p m="t-0 b-2">ID: {{ props.row.student.id }}</p>
          <h3>Course</h3>
          <el-table :data="props.row.courses" :border="childBorder">
            <el-table-column label="courseName" prop="coursename" />
            <el-table-column label="courseID" prop="courseid" />
            <el-table-column label="courseMark" prop="coursemark" />
             <el-table-column label="Option">
               <template #default="scope">
        <el-button size = "mini" type="danger" @click="handleDeleteCourse(scope.row.idcoursetable)">Drop</el-button>
      </template>
              </el-table-column>
          </el-table>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="Email" prop="student.email" />
    <el-table-column label="Name" prop="student.name" />


    <el-table-column label="Operations">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.row.student.id)"
          >Edit</el-button
        >
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.row.student.id)"
          >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span>This is modify page</span>

    <el-form :model="form" label-width="120px">
            <el-form-item label="Name">
      <el-input v-model="form.username" />
    </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save()">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>

    </div>
</template>

<script >
import request from "@/utils/request";
export default{
    name: 'AdminPage',
    components: {
        
    },

    data(){
      return{
            tableData: [ ],
            input: '',
            dialogVisible: false,
            id: '',
            form: {
      username: '', // Add other form fields if needed
    },
      }
    },

     
    methods: {
        goToHome() {
      // Use router. push to navigate to the homepage
      this.$router.push('/');
    },

    handleReset(){
          this.input = ""
    },

    handleDeleteCourse(id) {
    
      console.log(id);
      request.delete(`/register/${id}`).then(res => {
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
      this.handleSearch()
    })
  },

    handleEdit(id){
   this.id = id;
   this.dialogVisible = true;
   this.form = {};
},

save(){
  const formData = {     
                    studentId: this.id,     
                    studentName: this.form.username,
        };

        request.put("/changeName",formData).then(res => {
          console.log(res);
        });
        this.handleSearch();
        this.dialogVisible = false;

},

handleDelete(id){
  console.log(id);
  request.delete(`/student/${id}`).then(res => {
    console.log(res);
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
  })
  request.delete(`/enrol/${id}`).then(res => {
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
  })
  console.log("777")
      this.handleReset();
      this.handleSearch();
      
  },

handleSearch(){
       request.get("/student", {params: {
        email: this.input
       }}).then(res => {
        console.log(res);
          const { student, courses } = res.data;
            // Create an array containing student and course information
            this.tableData = [{ student, courses }];


       })
},

load(){
  
    }
}

}

</script>


<style scoped>
.custom-input {
  margin-top: 20px;
}
</style>
