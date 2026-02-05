import style from './page.module.css';
import SearchInput from '@/app/(afterLogin)/_component/SearchInput';
import Post from '@/app/(afterLogin)/_component/Post';
import Tabs from '@/app/(afterLogin)/search/_component/Tabs';
import BackButton from '@/app/(afterLogin)/_component/BackButton';

const Page = () => {
    return (
        <div className={style.container}>
            <section className={style.searchContainer}>
                <div className={style.searchWrapper}>
                    <BackButton />
                    <SearchInput />
                </div>
                <Tabs />
            </section>
            <section>
                <Post />
                <Post />
                <Post />
            </section>
        </div>
    );
};

export default Page;
