<template>
  <div class="preferences">
    <div id="table">
      <h2>Restaurant</h2>
      <h2>Categories</h2>
      <h2>Preference</h2>
    </div>

    <preference-input
      v-for="category in this.$store.state.preferences"
      v-bind:key="category.id"
      v-bind:category="category"
    />
    <!--
          <div id="indian-flex">
            <div id="text">
              Indian
              <label for="indian" id="text-pref">Prefferred</label>
            </div>
            <select
              name="indian"
              id="indian-selector"
              v-model="preferences.name"
              v-on:change="pushValue()"
            >
              <option disabled value=""></option>
              <option name="indian" value="like">Like</option>
              <option name="indian" value="dislike">Dislike</option>
            </select>
          </div> -->
    <!--</tr>
        <tr>
          <td>
            <div id="type">Breakfast</div>
          </td>
          <td>
            <label for="breakfast">Prefferred</label>
            <input id="breakfast" name="breakfast" type="radio" />
            <label for="breakfast">Dislike</label>
            <input id="breakfast" name="breakfast" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Mexican</td>
          <td>
            <label for="mexican">Prefferred</label>
            <input id="mexican" name="mexican" type="radio" />
            <label for="mexican">Dislike</label>
            <input id="mexican" name="mexican" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Japanese</td>
          <td>
            <label for="japanese">Prefferred</label>
            <input id="japanese" name="japanese" type="radio" />
            <label for="japanese">Dislike</label>
            <input id="japanese" name="japanese" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Italian</td>
          <td>
            <label for="italian">Prefferred</label>
            <input id="italian" name="italian" type="radio" />
            <label for="italian">Dislike</label>
            <input id="italian" name="italian" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Seafood</td>
          <td>
            <label for="seafood">Prefferred</label>
            <input id="seafood" name="seafood" type="radio" />
            <label for="seafood">Dislike</label>
            <input id="seafood" name="seafood" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Pizza</td>
          <td>
            <label for="pizza">Prefferred</label>
            <input id="pizza" name="pizza" type="radio" />
            <label for="pizza">Dislike</label>
            <input id="pizza" name="pizza" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Chinese</td>
          <td>
            <label for="chinese">Prefferred</label>
            <input id="chinese" name="chinese" type="radio" />
            <label for="chinese">Dislike</label>
            <input id="chinese" name="chinese" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Thai</td>
          <td>
            <label for="thai">Prefferred</label>
            <input id="thai" name="thai" type="radio" />
            <label for="thai">Dislike</label>
            <input id="thai" name="thai" type="radio" />
          </td>
        </tr>
        <tr>
          <td>Cajun</td>
          <td>
            <label for="cajun">Prefferred</label>
            <input id="cajun" name="cajun" type="radio" />
            <label for="cajun">Dislike</label>
            <input id="cajun" name="cajun" type="radio" />
          </td>
        </tr>
      </tbody>
    </table> -->
    <button
      v-on:click="
        storePreferences();
        $router.push('/');
      "
    >
      Approve
    </button>
  </div>
</template>

<script>
import preferenceService from "../services/PreferenceService";
import PreferenceInput from "./PreferenceInput";

export default {
  data() {
    return {
      preferences: [],
      category: {},
      showAddPreferences: false,
      newPreference: {
        type: "",
      },
    };
  },
  components: {
    PreferenceInput,
  },
  methods: {
    storePreferences() {
      //add foreach category in prefs here
      for (let i = 0; i < this.$store.state.preferences.length; i++) {
        let categoryElement = document.getElementById(
          this.$store.state.preferences[i].name
        );
        categoryElement.setAttribute("active", false);
        this.category = this.$store.state.preferences[i];
        this.category.value = categoryElement.value;
        this.preferences[i] = this.category;
        //this.$store.commit("UPDATE_PREFERENCE_VALUE", category.value, i);
      }
      this.$store.commit("UPDATE_PREFERENCES", this.preferences);
    },
    saveNewPreference() {
      preferenceService.addPreference(this.newPreference).then((response) => {
        if (response.status === 201) {
          this.showAddPreferences = false;
        }
      });
    },
  },
  created() {
    this.preferences = this.$store.state.preferences;
  },
};
</script>

<style>
div.preferences {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100vh;
  flex-grow: 1;
  align-items: center;
}

#add {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

input {
  width: 40vw;
  height: 5vh;
  border-radius: 9px;
  border: none;
  padding: 0;
  text-align: center;
  font-size: 20px;
  margin: 4px 2px;
}

#table {
  margin-left: 3vw;
  display: flex;
  font-size: 2vw;
  color: white;
  justify-content: space-between;
  align-items: center;
  width: 40vw;
  height: 8vh;
}
#header {
  color: white;
  font-family: "Courier New", Courier, monospace;
  text-align: center;
}
#rank {
  color: white;
  font-family: "Courier New", Courier, monospace;
  text-align: center;
}
td {
  color: white;
  font-family: "Courier New", Courier, monospace;
  size: 30px;
}
label {
  size: 60px;
}
#type {
  size: 50px;
}
</style>