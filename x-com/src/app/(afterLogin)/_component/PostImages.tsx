import style from './postImages.module.css';
import Link from 'next/link';
import Image from 'next/image';
import { User } from '@/model/User';
import clsx from 'clsx';

type Props = {
    post: { postId: number; content: string; User: User; createdAt: Date; Images: any[] };
};

const PostImages = ({ post }: Props) => {
    if (post.Images.length === 1) {
        return (
            post.Images &&
            post.Images.length > 0 && (
                <div className={style.oneImage}>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[0].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[0]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                </div>
            )
        );
    } else if (post.Images.length === 2) {
        return (
            post.Images &&
            post.Images.length > 0 && (
                <div className={clsx(style.gridImage, style.twoImage)}>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[0].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[0]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[1].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[1]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                </div>
            )
        );
    } else if (post.Images.length === 3) {
        return (
            post.Images &&
            post.Images.length > 0 && (
                <div className={clsx(style.gridImage, style.threeImage)}>
                    <Link
                        className={style.largeImage}
                        href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[0].imageId}`}
                    >
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[0]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[1].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[1]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[2].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[2]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                </div>
            )
        );
    } else if (post.Images.length === 4) {
        return (
            post.Images &&
            post.Images.length > 0 && (
                <div className={clsx(style.gridImage, style.fourImage)}>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[0].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[0]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[1].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[1]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[2].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[2]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                    <Link href={`/${post.User.id}/status/${post.postId}/photo/${post.Images[3].imageId}`}>
                        <div className={style.imageWrapper}>
                            <Image
                                className={style.image}
                                src={post.Images[3]?.link}
                                alt='게시물 이미지'
                                width={0}
                                height={0}
                                sizes='100vw'
                            />
                        </div>
                    </Link>
                </div>
            )
        );
    } else {
        return null;
    }
};

export default PostImages;
