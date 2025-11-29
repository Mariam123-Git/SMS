import axios from 'axios';

const BASE_URL = "/api";

export const sendOtp = async (username) => {
    return axios.post(`${BASE_URL}/send-otp`, { username });
};

export const verifyOtp = async (username, otp) => {
    return axios.post(`${BASE_URL}/verify-otp`, { username, otp });
};
