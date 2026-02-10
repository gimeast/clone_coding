interface UserID {
    id: string;
}

export interface User {
    id: string;
    name: string;
    image: string;
    Followers?: UserID[];
    _count?: {
        Followers: number;
        Followings: number;
    };
}
