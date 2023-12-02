<template>
    <div>
        This is Student Login
        <br>

         <el-button @click="goToHome" type="primary">Back to Home</el-button>

        <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="100px"
    class="demo-ruleForm"
  >

  <el-form-item label="Email" prop="email" :style="{ width: '900px' }">
      <el-input v-model.number="ruleForm.email" />
    </el-form-item>

    <el-form-item label="Password" prop="password" :style="{ width: '900px' }">
      <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
    </el-form-item>    


    <el-form-item>
      <el-button type="primary" @click="submitForm"
        >Submit</el-button
      >
      <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
    </el-form-item>
  </el-form>
    </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      ruleForm: {
        password: '',
        email: '',
      },
      rules: {
        password: [
          { required: true, message: 'Please input your password', trigger: 'change' },
        ],
        email: [
          { required: true, message: 'Please input your email', trigger: 'change' },
        ],
      },
    };
  },
  methods: {
    goToHome() {
      // Use router.push to navigate to the home page
      this.$router.push('/');
    },
    submitForm() {
      this.$refs.ruleFormRef.validate((valid) => {
        if (valid) {
          // Do something with the form data
          console.log('Form submitted:', this.ruleForm);
          request.post("/login", this.ruleForm).then(response => {
            if(response.code==='0'){
              console.log("you are right")
              const student = response.data;

            // Output student information
           console.log('Student ID:', student.id);
              console.log('Student Name:', student.name);

              this.$router.push({ name: 'PersonalPage', params: { studentId: student.id } });

            }
            else{
              console.log("failed")
            }
                console.log('Response from server:', response);
                   this.ruleForm.email = '';
                   this.ruleForm.password = '';
                }).catch(error => {
                   console.error('Error submitting form:', error);
                });
        } else {
          console.log('Form validation failed!');
        }
      });
    },
    resetForm() {
  this.$refs.ruleFormRef.resetFields();
},

    validateConfirmPass(rule, value, callback) {
      if (value !== this.ruleForm.password) {
        callback(new Error('Passwords do not match'));
      } else {
        callback();
      }
    },

    sentThemBack(){

    },

  },
};
</script>
