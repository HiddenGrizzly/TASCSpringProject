export interface AuthState {
    userId: string;
    username: string;
    roles: string[];
    refreshToken: string;
    accessToken: string;
}
