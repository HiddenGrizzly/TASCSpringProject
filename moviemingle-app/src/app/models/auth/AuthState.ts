export interface AuthState {
    userId: string;
    username: string;
    avatar: string | null;
    roles: string[];
    refreshToken: string;
    accessToken: string;
}
