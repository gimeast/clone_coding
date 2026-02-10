'use client';

import style from '@/app/(afterLogin)/_component/post.module.css';
import { ReactNode } from 'react';
import { User } from '@/model/User';
import { useRouter } from 'next/navigation';

type Props = {
    children: ReactNode;
    post: { postId: number; content: string; User: User; createdAt: Date; Images: any[] };
};

const PostArticle = ({ children, post }: Props) => {
    const router = useRouter();

    const handleClick = (e: React.MouseEvent) => {
        const target = e.target as HTMLElement;
        if (target.closest('a')) return;
        router.push(`/${encodeURIComponent(post.User.id)}/status/${post.postId}`);
    };

    return (
        <article onClick={handleClick} className={style.post}>
            {children}
        </article>
    );
};

export default PostArticle;
