import Image from 'next/image';
import style from './post.module.css';
import { Heart, MessageCircle, Repeat2 } from 'lucide-react';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import clsx from 'clsx';

dayjs.extend(relativeTime);

const Post = () => {
    const postDate = dayjs('2026-02-01 08:31:33').fromNow(true);
    const commented = false;
    const reposted = true;
    const liked = true;

    return (
        <article className={style.post}>
            <Image className={style.profile} src='/dummy_profile.webp' alt='프로필' width={50} height={50} />
            <div>
                <div className={style.box}>
                    <div className={style.postInfo}>
                        <span className={style.userName}>Elon Musk</span>
                        <span className={style.userId}>@elonmusk</span>
                        <time className={style.time}>{postDate}</time>
                    </div>
                    <p className={style.content}>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cumque eum facere nemo quos
                        voluptatem. Aperiam et exercitationem maiores maxime sequi. Aperiam commodi distinctio eos in
                        nisi perspiciatis porro quia quos.
                    </p>
                    <Image src='/img1.png' alt='내용 이미지' width={0} height={0} sizes='100vw' />
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
        </article>
    );
};

export default Post;
