<template>
  <nav class="navbar navbar-expand-md navbar-light nav-bg-zdy">
    <div class="container">
      <!--  首页    -->
      <a class="navbar-brand" href="/">网盘</a>
      <!--  导航栏的跳转   -->
      <div class="collapse navbar-collapse justify-content-center">
        <ul class="navbar-nav">
          <li class="nav-item mx-3">
            <router-link class="nav-link" to="/">文件</router-link>
          </li>
          <li class="nav-item mx-3">
            <router-link class="nav-link" to="/capacity">容量</router-link>
          </li>
          <li class="nav-item mx-3">
            <router-link class="nav-link" to="/music">qq音乐解码</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import icon from '../../public/icon.svg'

</script>

<style scoped>
.nav-bg-zdy {
  background-color: #e3f2fd;
}

.nav-bg-zdy .navbar-brand {
  letter-spacing: 0.5rem;
  font-weight: bold;
  color: #4F616D;
}

.navbar-nav > li > router-link {
  font-weight: bold;
  font-size: 1.2em;
  letter-spacing: 0.6em;
  border-bottom: 0.2em solid #e3f2fd;
  user-select: none;
  cursor: pointer;
}

.navbar-nav li .active {
  border-bottom: 0.2em solid black;
}

.navbar-nav > li > router-link:hover {
  border-bottom: 0.2em solid black;
}

</style>
