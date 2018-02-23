<template>
  <div>
    <b-nav pills>
      <b-nav-item active>Active</b-nav-item>
      <b-nav-item>Link</b-nav-item>
      <b-nav-item-dropdown id="nav7_ddown" text="台股" extra-toggle-classes="nav-link-custom" right>
        <b-dropdown-item>自選股</b-dropdown-item>
        <b-dropdown-divider></b-dropdown-divider>
        <b-dropdown-item>
          <router-link to="/importantNews/1">
            重點資訊  
          </router-link>
        </b-dropdown-item>
      </b-nav-item-dropdown>
    </b-nav>
  </div>
</template>