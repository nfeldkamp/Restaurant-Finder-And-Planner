<template>
  <div class="restaurant" v-bind:key="restaurant.id">
    <img id="rest-img" v-bind:src="restaurant.image_url" />
    <div id="rest-info">
      <!-- <router-link to="/restaurant/{restaurant.id}"> something like this for mobile version, hidden if browser-->
      <div id="rest_name_outer">
        <a
          href=""
          id="rest_name_inner"
          v-on:click.prevent="updateDisplay(restaurant)"
          >{{ restaurant.name }}</a
        >
      </div>
      <div id="pricerating">
        <div class="rating">
          Rating: {{ restaurant.rating }}
          <!--<img
            src="../assets/logo.png"
            v-bind:title="restaurant.rating + ' Star Review'"
            class="ratingStar"
            v-for="n in restaurant.rating"
            v-bind:key="n"
          />-->
        </div>

        <div id="price">
          {{ restaurant.price }}
        </div>
      </div>
      <div
        v-if="$route.path != '/invitations/user' && $route.name != 'guest-view'"
      >
        <!--<p id="rest_add">address placeholder</p>-->
        <p id="rest_add1">{{ restaurant.location.display_address[0] }}</p>
        <p id="rest_add2">{{ restaurant.location.display_address[1] }}</p>
      </div>
    </div>

    <div
      id="phonehours"
      v-if="
        $route.path != '/invitations/guestview' &&
        $route.path != '/invitations/user'
      "
    >
      <div
        v-if="$route.path != '/invitations/user' && $route.name != 'guest-view'"
      >
        <button v-if="restaurant.hours.is_open_now" class="openbtn">
          Open Now
        </button>
        <button v-if="!restaurant.hours.is_open_now" class="closedbtn">
          Closed
        </button>
      </div>
      <!-- <div id="hours">
        <p>Today's hours:</p>
        need a method to find todays day and pull hours off it
       <p>{{ restaurant.hourstoday }}</p> 
      </div>-->
      <button v-if="restaurant.display_phone" class="phonebtn">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill="currentColor"
          class="bi bi-phone-fill"
          viewBox="0 0 16 16"
          id="phoneicon"
        >
          <path
            d="M3 2a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V2zm6 11a1 1 0 1 0-2 0 1 1 0 0 0 2 0z"
          /></svg
        >Call Now
      </button>
    </div>
    <div v-if="$route.name == 'guest-view'" id="thumbs">
      <div
        id="thumbsup"
        v-bind:class="upclicked ? 'green' : 'black'"
        v-on:click="(upclicked = !upclicked), (downclicked = false)"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="bi bi-hand-thumbs-up"
          viewBox="0 0 16 16"
        >
          <path />
        </svg>
      </div>
      <div
        id="thumbsdown"
        v-bind:class="downclicked ? 'red' : 'black'"
        v-on:click="(downclicked = !downclicked), (upclicked = false)"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="bi bi-hand-thumbs-down"
          viewBox="0 0 16 16"
        >
          <path />
        </svg>
      </div>
    </div>
    <div v-if="$route.path == '/invitations/user'" id="thumbsuser">
      <div id="thumbsupuser">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="green"
          class="bi bi-hand-thumbs-up-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"
          />
        </svg>
        <div id="numthumbsup">1</div>
      </div>
      <div id="thumbsdownuser">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="red"
          class="bi bi-hand-thumbs-down-fill"
          viewBox="0 0 16 16"
        >
          <path
            d="M6.956 14.534c.065.936.952 1.659 1.908 1.42l.261-.065a1.378 1.378 0 0 0 1.012-.965c.22-.816.533-2.512.062-4.51.136.02.285.037.443.051.713.065 1.669.071 2.516-.211.518-.173.994-.68 1.2-1.272a1.896 1.896 0 0 0-.234-1.734c.058-.118.103-.242.138-.362.077-.27.113-.568.113-.856 0-.29-.036-.586-.113-.857a2.094 2.094 0 0 0-.16-.403c.169-.387.107-.82-.003-1.149a3.162 3.162 0 0 0-.488-.9c.054-.153.076-.313.076-.465a1.86 1.86 0 0 0-.253-.912C13.1.757 12.437.28 11.5.28H8c-.605 0-1.07.08-1.466.217a4.823 4.823 0 0 0-.97.485l-.048.029c-.504.308-.999.61-2.068.723C2.682 1.815 2 2.434 2 3.279v4c0 .851.685 1.433 1.357 1.616.849.232 1.574.787 2.132 1.41.56.626.914 1.28 1.039 1.638.199.575.356 1.54.428 2.591z"
          />
        </svg>
        <div id="numthumbsdown">2</div>
      </div>
    </div>
  </div>
</template>

<script>
//src="../assets/mock-up-rest-img.jpg"
//v-bind:src="restaurant.image"
export default {
  name: "restaurant-display",
  props: ["restaurant"],
  data() {
    return {
      upclicked: false,
      downclicked: false,
    };
  },
  methods: {
    updateDisplay(restaurant) {
      this.$store.commit("UPDATE_DISPLAY", restaurant);
    },
    thumbClicked() {
      if (this.upclicked == true) {
        this.upclicked = false;
      } else if (this.downclick == true) {
        this.downclick = false;
      }
    },
  },
  // created() {
  //   let name_font_box = document.getElementById("rest_name_outer");
  //   let name_font_size = document.getElementById("rest_name_inner");
  //   if (name_font_size.height() >= name_font_box.height()) {
  //     var fontsize = name_font_box.css("font-size");
  //     name_font_box.css("fontSize", parseFloat(fontsize) - 1);
  //   }
  // },
};
</script>

<style>
.restaurant {
  width: 32vw;
  height: 20vh;
  margin-top: 5px;
  display: flex;
  border-radius: 8px;
  border: 4px ridge darkgrey;
  background-color: lightgrey;
  /*border-style: ridge;*/
}

.black {
  fill: black;
}

.green {
  fill: green;
}

#thumbsup.black svg path {
  d: path(
    "M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"
  );
}

#thumbsdown.black svg path {
  d: path(
    "M8.864 15.674c-.956.24-1.843-.484-1.908-1.42-.072-1.05-.23-2.015-.428-2.59-.125-.36-.479-1.012-1.04-1.638-.557-.624-1.282-1.179-2.131-1.41C2.685 8.432 2 7.85 2 7V3c0-.845.682-1.464 1.448-1.546 1.07-.113 1.564-.415 2.068-.723l.048-.029c.272-.166.578-.349.97-.484C6.931.08 7.395 0 8 0h3.5c.937 0 1.599.478 1.934 1.064.164.287.254.607.254.913 0 .152-.023.312-.077.464.201.262.38.577.488.9.11.33.172.762.004 1.15.069.13.12.268.159.403.077.27.113.567.113.856 0 .289-.036.586-.113.856-.035.12-.08.244-.138.363.394.571.418 1.2.234 1.733-.206.592-.682 1.1-1.2 1.272-.847.283-1.803.276-2.516.211a9.877 9.877 0 0 1-.443-.05 9.364 9.364 0 0 1-.062 4.51c-.138.508-.55.848-1.012.964l-.261.065zM11.5 1H8c-.51 0-.863.068-1.14.163-.281.097-.506.229-.776.393l-.04.025c-.555.338-1.198.73-2.49.868-.333.035-.554.29-.554.55V7c0 .255.226.543.62.65 1.095.3 1.977.997 2.614 1.709.635.71 1.064 1.475 1.238 1.977.243.7.407 1.768.482 2.85.025.362.36.595.667.518l.262-.065c.16-.04.258-.144.288-.255a8.34 8.34 0 0 0-.145-4.726.5.5 0 0 1 .595-.643h.003l.014.004.058.013a8.912 8.912 0 0 0 1.036.157c.663.06 1.457.054 2.11-.163.175-.059.45-.301.57-.651.107-.308.087-.67-.266-1.021L12.793 7l.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315l-.353-.354.353-.354c.047-.047.109-.176.005-.488a2.224 2.224 0 0 0-.505-.804l-.353-.354.353-.354c.006-.005.041-.05.041-.17a.866.866 0 0 0-.121-.415C12.4 1.272 12.063 1 11.5 1z"
  );
}

.red svg path {
  d: path(
    "M6.956 14.534c.065.936.952 1.659 1.908 1.42l.261-.065a1.378 1.378 0 0 0 1.012-.965c.22-.816.533-2.512.062-4.51.136.02.285.037.443.051.713.065 1.669.071 2.516-.211.518-.173.994-.68 1.2-1.272a1.896 1.896 0 0 0-.234-1.734c.058-.118.103-.242.138-.362.077-.27.113-.568.113-.856 0-.29-.036-.586-.113-.857a2.094 2.094 0 0 0-.16-.403c.169-.387.107-.82-.003-1.149a3.162 3.162 0 0 0-.488-.9c.054-.153.076-.313.076-.465a1.86 1.86 0 0 0-.253-.912C13.1.757 12.437.28 11.5.28H8c-.605 0-1.07.08-1.466.217a4.823 4.823 0 0 0-.97.485l-.048.029c-.504.308-.999.61-2.068.723C2.682 1.815 2 2.434 2 3.279v4c0 .851.685 1.433 1.357 1.616.849.232 1.574.787 2.132 1.41.56.626.914 1.28 1.039 1.638.199.575.356 1.54.428 2.591z"
  );
}

.green svg path {
  d: path(
    "M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"
  );
}

.red {
  fill: red;
}

#thumbs {
  height: 3vw;
  width: 3vw;
  margin-right: 0.5vw;
}

#thumbsuser {
  height: 2.5vw;
  width: 2.5vw;
  margin-right: 1vw;
}

#thumbsup {
  margin-top: 1.5vw;
  margin-bottom: 1vw;
}

#thumbsupuser {
  margin-top: 0.5vw;
  margin-bottom: 0.3vw;
}

#numthumbsup {
  margin-left: 1vw;
}

#numthumbsdown {
  margin-left: 1vw;
}

#rest-img {
  height: 18vh;
  width: 20vh;
  margin-left: 3px;
  margin-top: 8px;
  margin-right: 10px;
  flex-grow: 1;
}

img.ratingStar {
  margin-top: 8px;
  margin-bottom: 3px;
  height: auto;
  width: 1vw;
}

#rest-info {
  display: flex;
  flex-direction: column;
  flex-grow: 3;
}

#rest_type {
  margin-top: 0px;
  font-size: 2vh;
  line-height: 4vh;
  margin-left: 2px;
}

#rest_name {
  margin-bottom: 1vh;
  margin-top: 2vh;
  flex-direction: start;
  font-size: 4vh;
  line-height: 1.6vh;
}

#rest_add1 {
  margin-bottom: 0px;
  margin-top: 1vw;
}

#rest_add2 {
  margin-bottom: 0px;
  margin-top: 0px;
  line-height: 1.2vw;
}

#phonehours {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-end;
  flex-grow: 1;
}

#phonehours button {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 8vw;
  border-radius: 8px;
  border: none;
  padding: 5px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
}

button.openbtn,
.phonebtn {
  height: 4.5vh;
}

button.openbtn {
  background-color: lightblue;
}

button.closedbtn {
  background-color: red;
  height: 4.5vh;
}

#phonehours img {
  margin-right: 2px;
  height: 2vh;
  width: 2vw;
}

#phonehours p {
  margin-top: 4px;
  margin-bottom: 0px;
  margin-right: 10px;
}

#hours {
  flex-grow: 3;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#pricerating {
  display: flex;
  height: 1.5vw;
  width: 10vw;
  justify-content: space-between;
}

#price {
  margin-top: 0.3vw;
}

#dot {
  margin-top: 0;
  font-size: 4vh;
}

#phoneicon {
  margin-right: 0.5vw;
}
</style>
