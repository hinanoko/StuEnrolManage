<template>
  <div class="home-view">
      
      <div class="content-wrapper">
      <div class="text-container">
        <h1 class="words-style">Enrolment Management System</h1>
      </div>
      <img
        src="@\assets\v2-3a81860344e191ceab1acaf6e5c7db9a_720w.png"
        alt="Your Image"
        class="custom-image"
      />
    </div>
   
   
  </div>
</template>

<script>
// @ is an alias to /src
export default {
  name: 'HomeView',
  components: {
  }
}
</script>


<style scoped>
.home-view {
  /*Set the style of the Home View container, which can be adjusted as needed*/
  text-align: center;
  padding: 20px;
}

.custom-image {
  /*Set the style of the image and adjust it as needed*/
  width: 800px;
  height: 600px;
  margin-top: 40px; /*Top margin*/
  margin-left: 100px; /*Left margin, set to auto to center*/
  margin-right: 100px; /*Right margin, set to auto to center*/
}

.content-wrapper {
  display: flex;
  align-items: center; /*Vertical center*/
}

.text-container {
 margin-top: 20px;
  margin-left: 80px; /*Left margin, set to auto to center*/
  margin-right: 20px; /*Right margin, set to auto to center*/
  margin-bottom: 500px;
  text-align: left;
  flex-direction: column; /*Set vertical arrangement*/
}

.words-style {

  font-size: 24px; /* Set the text size, which can be adjusted as needed */
  margin-top: 20px;
  margin-left: 80px; /* Left margin, set to auto to center */
  margin-right: 20px; /* Right margin, set to auto to center */
  margin-bottom: 10px;
}
</style>