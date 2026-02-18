import Image from 'next/image';
import style from './post.module.css';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import PostArticle from '@/app/(afterLogin)/_component/PostArticle';
import Link from 'next/link';
import { faker } from '@faker-js/faker/locale/ko';
import PostImages from '@/app/(afterLogin)/_component/PostImages';
import PostButtonGroup from '@/app/(afterLogin)/_component/PostButtonGroup';

dayjs.extend(relativeTime);

interface Props {
    noImage?: boolean;
}

const Post = ({ noImage }: Props) => {
    const target = {
        postId: 1,
        User: {
            id: 'Elon_Musk',
            name: 'elonmusk',
            image: '/dummy_profile.webp',
        },
        content: faker.lorem.text(),
        createdAt: new Date(),
        Images: [] as any[],
    };

    if (Math.random() > 0.5 && !noImage) {
        target.Images.push({ imageId: 1, link: faker.image.urlPicsumPhotos() });
        target.Images.push({ imageId: 2, link: faker.image.urlPicsumPhotos() });
        target.Images.push({ imageId: 3, link: faker.image.urlPicsumPhotos() });
        target.Images.push({ imageId: 4, link: faker.image.urlPicsumPhotos() });
    }

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
                    <PostImages post={target} />
                </div>
                <PostButtonGroup />
            </div>
        </PostArticle>
    );
};

export default Post;
