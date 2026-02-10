import Image from 'next/image';
import style from './post.module.css';
import { Heart, MessageCircle, Repeat2 } from 'lucide-react';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import clsx from 'clsx';
import PostArticle from '@/app/(afterLogin)/_component/PostArticle';
import Link from 'next/link';

dayjs.extend(relativeTime);

const Post = () => {
    const target = {
        postId: 1,
        User: {
            id: 'Elon_Musk',
            name: 'elonmusk',
            image: '/dummy_profile.webp',
        },
        content: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cumque eum facere nemo quos',
        createdAt: new Date(),
        Images: ['/img1.png'],
    };

    const commented = false;
    const reposted = true;
    const liked = true;

    return (
        <PostArticle post={target}>
            <Image className={style.profile} src={target.User.image} alt='프로필' width={50} height={50} />
            <div>
                <div className={style.box}>
                    <div className={style.postInfo}>
                        <Link href={`/${target.User.id}`} className={style.userName}>
                            {target.User.name}
                        </Link>
                        <span className={style.userId}>@{target.User.id}</span>
                        <time className={style.time}>{dayjs(target.createdAt).fromNow(true)}</time>
                    </div>
                    <p className={style.content}>{target.content}</p>
                    <Image src={target.Images[0]} alt='게시물 이미지' width={0} height={0} sizes='100vw' />
                </div>
                <div className={style.buttonContainer}>
                    <button className={clsx(style.commentBtn, commented && style.commented)}>
                        <MessageCircle />
                    </button>
                    <button className={clsx(style.repostBtn, reposted && style.reposted)}>
                        <Repeat2 />
                    </button>
                    <button className={clsx(style.likeBtn, liked && style.liked)}>
                        <Heart />
                    </button>
                </div>
            </div>
        </PostArticle>
    );
};

export default Post;
