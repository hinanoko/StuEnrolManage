<template>
  <div>
    <p>Student ID: {{ studentData.id }}</p>

    <el-button @click = "goToEnrol" type = "primary">Enrol</el-button>
    <el-button @click = "goToDrop" type = "primary">Drop</el-button>
    <el-button @click = "goToShow" type = "primary">Show</el-button>
    <el-button @click = "goToChange" type = "primary">Change</el-button>
    <el-button @click = "backToStudent" type = "primary">Exit</el-button>
  </div>
</template>

<script>

export default {
  name: 'PersonalPage',
  components: {},
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
      // Obtain student data carried through routing parameters
      studentData: {},
    };
  },
   methods: {
    // You need to replace the function that simulates asynchronous acquisition of student data with the actual data acquisition logic
    async getStudentDataById(studentId) {
      // This can be the actual data acquisition logic, such as requesting through an interface
      // Here, setTimeout is used to simulate asynchronous requests, and you need to replace them according to the actual situation
      return new Promise((resolve) => {
        setTimeout(() => {
          // What is returned here is the passed student ID and name
          resolve({ id: studentId});
        }, 1000); // Simulate a 1-second delay
      });
    },

    goToEnrol() {
      // Use router.push to navigate to the home page
      this.$router.push({name: 'Enrol', params: { studentId: this.studentData.id } });
    },

    goToDrop() {

      this.$router.push({name: 'Drop', params: {studentId: this.studentData.id}})
    },

    goToShow() {

      this.$router.push({name: 'Show', params: {studentId: this.studentData.id}})
    },

    goToChange() {
      this.$router.push({name: 'Change', params: {studentId: this.studentData.id}})
    },

    backToStudent(){
      this.$router.push('/login');
    }
  },
};
</script>
