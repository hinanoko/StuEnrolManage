<template>
   
<div class="header">
    <router-link to="/student" class="nav-button">Go to Register Page</router-link>
    <div class="spacer"></div> <!-- 一定距离的间隔 -->
    <router-link to="/admin" class="nav-button">Go to Admin Page</router-link>
    <div class="spacer"></div> <!-- 一定距离的间隔 -->
    <router-link to="/login" class="nav-button">Go to Student Page</router-link>
</div>

</template>

<style scoped>
.header {
  height: 50px;
  width: 100%; /* 占据整个页面的长度 */
  background-color: #f0f0f0; /* 背景颜色，可根据需要调整 */
  border-bottom: 2px solid #ccc; /* 分界线 */
  box-sizing: border-box; /* 防止边框影响元素总宽度 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.nav-button {
  padding: 10px 20px;
  text-decoration: none;
  background-color: #3498db;
  color: #fff;
  border-radius: 5px;
}

.spacer {
  width: 190px; /* 调整间隔的宽度 */
}
</style>

<script>
   export default{

   }
</script>
