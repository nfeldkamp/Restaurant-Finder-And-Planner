<template>
  <div class="restaurant-details" v-bind:key="restaurant.id">
    <img id="detail-img" v-bind:src="restaurant.image_url" />
    <div id="detail-info">
      <div id="outer-rest-name">
        <p id="detail-rest-name">{{ restaurant.name }}</p>
      </div>

      <div id="detail-pricerating">
        <div class="detail-rating">
          Rating: {{ restaurant.rating }}
          <!--<img
              src="../assets/logo.png"
              class="detail-star"
              v-for="n in restaurant.rating"
              v-bind:key="n"
            /> -->
        </div>
        <div class="detail-price">
          {{ restaurant.price }}
        </div>
      </div>
      <div v-if="$route.name != 'guest-view'">
        <p id="rest_add1-details">
          {{ restaurant.location.display_address[0] }}
        </p>
        <p id="rest_add2-details">
          {{ restaurant.location.display_address[1] }}
        </p>
        <div id="detail-hours">
          Hours of Operation:
          <div id="hours-data">
            <div
              id="hour-loop"
              v-for="hour in this.thisHours"
              v-bind:key="hour.id"
              v-bind:hour="hour"
            >
              {{ hour }}<br />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="$route.name != 'guest-view'">
      <div id="detail-phonehours">
        <button v-if="restaurant.hours.is_open_now" class="detail-openbtn">
          Open Now
        </button>
        <button v-if="!restaurant.hours.is_open_now" class="detail-closedbtn">
          Closed
        </button>
        <button v-if="restaurant.display_phone" class="detail-phonebtn">
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
    </div>
  </div>
</template>

<script>
//src="../assets/mock-up-rest-img.jpg"
//v-bind:src="restaurant.image"
export default {
  name: "restaurant-details",
  props: ["restaurant"],
  data() {
    return {
      hours: [],
      thisHours: [],
    };
  },
  created() {
    let thisRestaurant = this.restaurant.hours[0].open;
    for (let i = 0; i < thisRestaurant.length; i++) {
      let hoursOpen;
      let hoursClosed;
      if (thisRestaurant[i].start >= 1200) {
        thisRestaurant[i].start -= 1200;
        hoursOpen = `${thisRestaurant[i].start} PM`;
      } else if (thisRestaurant[i].start < 1200) {
        hoursOpen = `${thisRestaurant[i].start} AM`;
      }
      if (thisRestaurant[i].end >= 1200) {
        thisRestaurant[i].end -= 1200;
        hoursClosed = `${thisRestaurant[i].end} PM`;
      } else if (thisRestaurant[i].end < 1200) {
        hoursClosed = `${thisRestaurant[i].end} AM`;
      }
      if (thisRestaurant[i].day == 0) {
        this.thisHours.push(`Monday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 1) {
        this.thisHours.push(`Tuesday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 2) {
        this.thisHours.push(`Wednesday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 3) {
        this.thisHours.push(`Thursday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 4) {
        this.thisHours.push(`Friday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 5) {
        this.thisHours.push(`Satuday: ${hoursOpen} ${hoursClosed}`);
      }
      if (thisRestaurant[i].day == 6) {
        this.thisHours.push(`Sunday: ${hoursOpen} ${hoursClosed}`);
      }
    }
  },
  // created() {
  //   let name_font_box = document.getElementById("outer-rest-name");
  //   let name_font_size = document.getElementById("detail-rest-name");
  //   if (name_font_size.height() >= name_font_box.height()) {
  //     var fontsize = name_font_box.css("font-size");
  //     name_font_box.css("fontSize", parseFloat(fontsize) - 1);
  //   }
  // },
};
</script>

<style>
#detail-info {
  width: 30vw;
}
.restaurant-details {
  width: 67vw;
  height: 80vh;
  margin-top: 5px;
  display: flex;
  border-radius: 8px;
  border: 4px ridge darkgrey;
  background-color: lightgrey;
  /*border-style: ridge;*/
}

#rest_add1-details {
  margin-bottom: 0px;
  margin-top: 1vw;
}

#rest_add2-details {
  margin-bottom: 0px;
  margin-top: 0px;
  line-height: 1.2vw;
}

#rest_add1-details,
#rest_add2-details {
  font-size: 1.5vw;
}

#detail-rest-name {
  display: flex;
  flex-wrap: nowrap;
}

#detail-img {
  height: 25vw;
  width: 25vw;
  margin-left: 3px;
  margin-top: 8px;
  margin-right: 10px;
  flex-grow: 1;
}

img.detail-star {
  margin-bottom: 1vh;
  height: auto;
  width: 3vw;
}

.detail-rating {
  margin-top: 3vh;
}

.detail-price {
  margin-top: 0.8vw;
  font-size: 2vw;
}

#detail-rest-type {
  margin-top: 0px;
  font-size: 3vh;
  line-height: 4vh;
  margin-left: 2px;
  margin-bottom: 1vh;
}

#detail-rest-add {
  margin-bottom: 0px;
  margin-top: 1vh;
}

#detail-rest-info {
  display: flex;
  flex-direction: column;
  flex-grow: 3;
}

#detail-rest-name {
  margin-bottom: 1vh;
  margin-top: 4vh;
  flex-direction: start;
  font-size: 3vw;
  line-height: 1.6vh;
}

#detail-phonehours {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-end;
  flex-grow: 1;
}

#detail-phonehours button {
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

button.detail-openbtn,
.detail-phonebtn,
.detail-closedbtn {
  height: 5vh;
}

button.detail-openbtn {
  background-color: lightblue;
}

button.detail-closedbtn {
  background-color: red;
}

#detail-phonehours img {
  margin-right: 2px;
  height: 2vh;
  width: 2vw;
}

#detail-phonehours p {
  margin-top: 4px;
  margin-bottom: 0px;
  margin-right: 10px;
}

#detail-hours {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  margin-top: 3vh;
  margin-bottom: 0px;
  line-height: 0px;
}

#hours-data {
  margin-top: 1vw;
}

#hours-data p {
  margin-top: 2.75vh;
  margin-bottom: 1vh;
}

#detail-pricerating {
  display: flex;
  flex-direction: column;
}

#phoneicon {
  margin-right: 0.5vw;
}

#hour-loop {
  margin-top: 1.5vw;
}
</style>
