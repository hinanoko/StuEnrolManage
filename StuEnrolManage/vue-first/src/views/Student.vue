<template>
  <div>
    This is Student
    <br>
    <el-button @click="goToHome" type="primary">Back to Home</el-button>

     <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="Email" prop="email">
        <el-input v-model="ruleForm.email" />
        <div class="emailValidationMessage">{{ emailValidationMessage }}</div>
      </el-form-item>

      <el-form-item label="Password" prop="pass">
        <el-input
          v-model="ruleForm.pass"
          type="password"
          autocomplete="off"
        />
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

      <el-form-item label="Name" prop="name">
        <el-input v-model.number="ruleForm.name" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">Submit</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
    <span v-if="passValid" style="color: green;">Password format is correct</span>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
 
  name: 'StudentPage',
  data() {
    return {
      ruleForm: {
      
      },
      emailValidationMessage: '',
      rules: {

         email: [
          {
            required: true,
  message: 'Please input your email',
  trigger: 'change',
  validator: (rule, value, callback) => {
    //Add a judgment here, and if the value is empty, perform the corresponding operation
    if (!value.trim()) {
      //Perform the actions you need, such as turning off green text
      this.emailValidationMessage = '';
      //Trigger verification callback
      callback(new Error('Please input your email'));
    } else {
      //If the value is not empty, perform verification normally
      callback();
    }
  }
          },
        { validator: this.validateEmailFormat, trigger:'change' }
        ],

        pass: [
  {
  required: true,
  message: 'Please input your password',
  trigger: 'change',
  validator: (rule, value, callback) => {
    //Add a judgment here, and if the value is empty, perform the corresponding operation
    if (!value.trim()) {
      // 执行你需要的操作，例如关闭绿字
      this.passValidationMessage = '';
      //Trigger verification callback
      callback(new Error('Please input your password'));
    } else {
      //If the value is not empty, perform verification normally
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
    //Add a judgment here, and if the value is empty, perform the corresponding operation
    if (!value.trim()) {
      //Perform the actions you need, such as turning off green text
      this.confirmPassValidationMessage = '';
     
      callback(new Error('Please input your password'));
    } else {
      //If the value is not empty, perform verification normally
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
    goToHome() {
      //Use router. push to navigate to the homepage
      this.$router.push('/');
    },
      submitForm() {
        console.log("first error")

        
    this.checkDuplicateEmail(this.ruleForm.email).then(res => {
      console.log("notice")
      console.log(res)
    if (res) {
      //Email duplicate, prompt user
      this.$message({
        type: "error",
        message: "Email already exists. Please use a different email.",
      });
    } else {
      //Email not duplicated, perform form verification
      this.$refs.ruleFormRef.validate((valid) => {
        if (valid) {
          //Other validation logic
          // ...
          console.log("second error")
          // Check if the first three items meet the criteria
          if (this.emailValidationMessage === 'Right Email format' &&
              this.passValidationMessage === 'Right password format' &&
              this.confirmPassValidationMessage === 'Passwords match') {
     console.log("third error")
                const formData = {
                 
          email: this.ruleForm.email,
          password: this.ruleForm.pass,
          name: this.ruleForm.name
        };
        console.log(formData)

                request.post("/student", formData).then(res => {
                  if(res.code==='0'){
                    console.log("fourth error")
           this.$message(
        {
          type: "success",
          message: "change new success"
        }
           ),
           this.ruleForm.email = "";
            this.ruleForm.pass = "";
            this.ruleForm.checkPass = "";
            this.ruleForm.name = "";
            this.emailValidationMessage = "";
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
        })
    } 
        }


      )},
      



    resetForm() {
      this.emailValidationMessage = ''; 
      this.passValidationMessage = ''; 
      this.confirmPassValidationMessage = '';
      this.$refs.ruleFormRef.resetFields();
      
    },
    validateEmail() {
      // Trigger email validation when input changes
     this.$refs.ruleFormRef.validateField('email', (errorMsg) => {
        this.emailValid = !errorMsg;
      });
    },
    validateEmailFormat(rule, value, callback) {
      // Custom validator for email format
      const emailRegex = /^[a-zA-Z]+\.[a-zA-Z]+@university\.com$/;
      if (value && !emailRegex.test(value)) {
    
        callback(new Error('Invalid email format'));
        this.emailValidationMessage = ''; 
      } 
      else {
       
       this.emailValidationMessage = 'Right Email format';
    callback();
      }
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
    
      } else {
      
        this.passValidationMessage = 'Right password format';
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

   checkDuplicateEmail(email) {
  //Send a request to the backend to check if the email is duplicated
  return new Promise((resolve, reject) => {
    request.get("/changeName", {
      params: {
        email: email
      }
    })
    .then(res => {
      console.log(res.data);
      resolve(res.data); //Solve with Boolean values
    })
    .catch(error => {
      reject(error); //Refuse on error
    });
  });
}

  }
};
</script>

<style>
.emailValidationMessage {
  color: green; /* Set text color to green */
}
.passValidationMessage {
  color: green; /* Set text color to green */
}
.confirmPassValidationMessage {
  color: green; /* Set text color to green */
}
</style>
