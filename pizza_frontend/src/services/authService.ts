import axios from 'axios';
import { User } from '../models/user';
import { ApiResponse } from '../models/ApiResponse';

const API_URL = 'http://localhost:8080/api/users';

export class AuthService {
    private static API_URL = `${API_URL}`;

    public static async signup(user: User): Promise<ApiResponse<User>> {
        const response = await axios.post<ApiResponse<User>>(`${this.API_URL}/register`, user, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true
        });
        return response.data;
    }

    public static async login(email: string, password: string): Promise<ApiResponse<User>> {
        const data = { email, password };
        const response = await axios.post<ApiResponse<User>>(`${API_URL}/login`, data, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true
        });
        return response.data;
    }
}