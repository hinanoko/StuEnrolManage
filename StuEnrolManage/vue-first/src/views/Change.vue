<template>
    <div>
        <p>{{ "Hello, this is a line of text." }}</p>
        <p>Welcome, Student ID: {{ studentData.id }}</p>

         <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="120px"
    class="demo-ruleForm"
  >
    <el-form-item label="Password" prop="pass">
      <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
      <div class="passValidationMessage">{{ passValidationMessage }}</div>
    </el-form-item>

    <el-form-item label="Confirm" prop="checkPass">
      <el-input
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
      <div class="confirmPassValidationMessage">{{ confirmPassValidationMessage }}</div>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)"
        >Submit</el-button
      >
      <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
    </el-form-item>
  </el-form>

    </div>
</template>


<script>
import request from "@/utils/request";
export default{
    name: "ChangePage",

    components: {
        
    },

    mounted() {
  try {
    const studentId = this.$route.params.studentId;
    //Simulate the process of asynchronous acquisition of student data
    this.getStudentDataById(studentId).then(studentData => {
      // 将获取到的学生数据赋值给 studentData
      this.studentData = studentData;

      //Call the load method after obtaining the student data successfully
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
    ruleForm: {},

    emailValidationMessage: '',

     rules: {
       pass: [
  {
  required: true,
  message: 'Please input your password',
  trigger: 'change',
  validator: (rule, value, callback) => {
    // Add a judgment here, and if the value is empty, perform the corresponding operation
    if (!value.trim()) {
      // Perform the actions you need, such as turning off green text
      this.passValidationMessage = '';
      // Trigger verification callback
      callback(new Error('Please input your password'));
    } else {
      // If the value is not empty, perform verification normally
      callback();
    }
  }
},
  { validator: this.validatePassFormat, trigger:'change' }
],
        checkPass: [
          {
  required: true,
  message: 'Please input your password',
  trigger: 'change',
  validator: (rule, value, callback) => {
    // Add a judgment here, and if the value is empty, perform the corresponding operation
    if (!value.trim()) {
      // Perform the actions you need, such as turning off green text
      this.confirmPassValidationMessage = '';
      // Trigger verification callback
      callback(new Error('Please input your password'));
    } else {
      // If the value is not empty, perform verification normally
      callback();
    }
  }
},
          { validator: this.validateConfirmPass, trigger: 'change' },
       ],
      },
  };
},


methods: {
    getStudentDataById(studentId) {
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

    submitForm() {
              console.log("first error")
       this.$refs.ruleFormRef.validate((valid) => {
        if (valid) {
          console.log("second error")
          // Check if the first three items meet the criteria
          if (
              this.passValidationMessage === 'Right password format' &&
              this.confirmPassValidationMessage === 'Passwords match') {
     console.log("third error")
                const formData = {     
                    studentId: this.studentData.id,     
          password: this.ruleForm.pass,
        };
        console.log(formData)

                request.put("/student", formData).then(res => {
                  if(res.code==='0'){
                    console.log("fourth error")
           this.$message(
        {
          type: "success",
          message: "change new success"
        }
           ),
            this.ruleForm.pass = "";
            this.ruleForm.checkPass = "";
              this.passValidationMessage = "";
              this.confirmPassValidationMessage = "";
      }
      else{
        console.log("fifth error")
        this.$message(
        {
          type: "error",
          message: res.msg
        }
        )
      }
                })
                console.log("sixth error")
        
      } else {
        console.log("seventh error")
        alert('Confirmation failed. Please check the form.');
      }
        } else {
          console.log('Form validation failed!');
        }
      });
    },

    resetForm(){
         this.emailValidationMessage = ''; 
      this.passValidationMessage = ''; 
      this.confirmPassValidationMessage = '';
      this.ruleForm.pass = '';  // Clear the value of the password box
  this.ruleForm.checkPass = '';  // Clear the value of the confirm password box
      this.$refs.ruleFormRef.resetFields();
      
    },


     validatePass() {
      // Trigger email validation when input changes
      this.$refs.ruleFormRef.validateField('pass', (errorMsg) => {
        this.passValid = !errorMsg;
      });
    },


    validatePassFormat(rule, value, callback) {
      // Custom validator for email format
      const passRegex = /^[A-Z][a-zA-Z]{4,}[0-9]{3,}$/;
      if (value && !passRegex.test(value)) {
    
        callback(new Error('Invalid password format'));
        this.passValidationMessage = ''; 
    
      } 
      else {
      
         this.passValidationMessage = value ? 'Right password format' : '';
         callback();
      }
    },
    validateConfirmPass(rule, value, callback) {
    if (value !== this.ruleForm.pass) {
      callback(new Error('Passwords do not match'));
      this.confirmPassValidationMessage = ''; 
    } else {
      this.confirmPassValidationMessage = 'Passwords match';
      callback();
    }
  },




}
}
</script>


<style>
.passValidationMessage {
  color: green; /* Set text color to green */
}
.confirmPassValidationMessage {
  color: green; /* Set text color to green */
}
</style>
