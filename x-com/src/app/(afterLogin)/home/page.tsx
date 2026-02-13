import style from './page.module.css';
import Post from '@/app/(afterLogin)/_component/Post';
import PostForm from '@/app/(afterLogin)/_component/PostForm';

const Home = () => {
    return (
        <div className={style.container}>
            <nav className={style.tabs}>
                <button>For you</button>
                <button>Following</button>
            </nav>
            <PostForm />
            <Post />
            <Post />
            <Post />
            <Post />
        </div>
    );
};

export default Home;
