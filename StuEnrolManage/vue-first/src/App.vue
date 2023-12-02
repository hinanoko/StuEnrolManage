<template>
     <div>
         <Header>
         </Header>
         <router-view></router-view>
      </div>
</template>

<style>

</style>

<script>
import Header from "@/components/Header.vue"
export default{
  name: "Everyone",
  components: {
       Header,
  },
}
</script>

