import axios from 'axios';

export default {
    addPreference(preference) {
        return axios.post('preferences', preference)
    }
}