'use client';

import style from './post.module.css';
import { Heart, MessageCircle, Repeat2 } from 'lucide-react';
import clsx from 'clsx';

const PostButtonGroup = () => {
    const commented = false;
    const reposted = true;
    const liked = true;

    const handleComment = (e: React.MouseEvent) => {
        e.stopPropagation();
    };
    const handleRepost = (e: React.MouseEvent) => {
        e.stopPropagation();
    };
    const handleLike = (e: React.MouseEvent) => {
        e.stopPropagation();
    };

    return (
        <div className={style.buttonContainer}>
            <button
                type='button'
                onClick={handleComment}
                className={clsx(style.commentBtn, commented && style.commented)}
            >
                <MessageCircle />
            </button>
            <button type='button' onClick={handleRepost} className={clsx(style.repostBtn, reposted && style.reposted)}>
                <Repeat2 />
            </button>
            <button type='button' onClick={handleLike} className={clsx(style.likeBtn, liked && style.liked)}>
                <Heart />
            </button>
        </div>
    );
};

export default PostButtonGroup;
