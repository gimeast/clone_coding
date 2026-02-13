import style from './page.module.css';
import BackButton from '@/app/(afterLogin)/_component/BackButton';
import Post from '@/app/(afterLogin)/_component/Post';
import PostForm from '@/app/(afterLogin)/_component/PostForm';

const Page = () => {
    return (
        <div className={style.container}>
            <div className={style.top}>
                <BackButton />
                <h2>게시하기</h2>
            </div>
            <Post />
            <PostForm />
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
