import style from './page.module.css';
import BackButton from '@/app/(afterLogin)/_component/BackButton';
import Post from '@/app/(afterLogin)/_component/Post';
import CommentForm from '@/app/(afterLogin)/_component/CommentForm';

type Props = {
    params: Promise<{ username: string; postId: string; photoId: string }>;
};
const Page = async ({ params }: Props) => {
    const { postId } = await params;
    return (
        <div className={style.container}>
            <div className={style.top}>
                <BackButton />
                <h2>게시하기</h2>
            </div>
            <Post />
            <CommentForm postId={postId} />
            <ul>
                <li>
                    <Post />
                </li>
                <li>
                    <Post />
                </li>
            </ul>
        </div>
    );
};

export default Page;
