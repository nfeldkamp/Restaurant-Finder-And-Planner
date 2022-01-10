import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    //fix this
    getInvitationByGuestEmail(guestEmail) {
        return http.get(`/invitations/guestview/${guestEmail}`);
    },
}
