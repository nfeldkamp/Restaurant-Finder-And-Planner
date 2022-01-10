<template>
  <div id="style-flex" v-bind:key="category.id">
    <div id="text">
      {{ category.name }}
    </div>
    <div id="text2">
      <label id="text-pref">Preferred</label>
    </div>
    <select
      v-bind:name="category.name"
      v-bind:id="category.name"
      v-bind:value="category.value"
    >
      <option value=""></option>
      <option v-bind:name="category.name" value="like">Like</option>
      <option v-bind:name="category.name" value="dislike">Dislike</option>
    </select>
  </div>
</template>

<script>
export default {
  name: "preference-input",
  props: ["category"],
};
</script>

<style scoped>
#style-flex {
  display: flex;
  width: 40vw;
  justify-content: space-between;
  margin-left: 3vw;
  align-items: center;
}

#style-flex #text,
#style-flex #text2 {
  font-size: 2vw;
  color: white;
}

#style-flex #text #text-pref {
  margin-left: 3vw;
}

#style-flex select {
  height: 2vw;
  width: 6vw;
}
</style>