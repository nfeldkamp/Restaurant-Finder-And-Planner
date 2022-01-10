import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    buildTheMonster(invitation) {
        return http.post('/invitations', invitation);
    },

    getInvitations() {
        return http.get('/invitations/user');
    }

}
