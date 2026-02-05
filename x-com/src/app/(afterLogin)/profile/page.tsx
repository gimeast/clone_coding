import style from './page.module.css';
import BackButton from '@/app/(afterLogin)/_component/BackButton';
import Post from '@/app/(afterLogin)/_component/Post';
import Image from 'next/image';

const Page = () => {
    const user = {
        id: 'elon',
        name: 'Elon musk',
        image: '',
        Followers: [],
        _count: { Followers: 0, Followings: 0 },
    };
    return (
        <div>
            <section className={style.profileContainer}>
                <div className={style.top}>
                    <BackButton />
                    {user.name}
                </div>
                <div className={style.profileBox}>
                    <div className={style.profile}>
                        <Image src='/dummy_profile.webp' alt='프로필이미지' width={120} height={120} />
                        <div className={style.userInfo}>
                            <span className={style.userName}>{user.name}</span>
                            <span className={style.userId}>@{user.id}</span>
                        </div>
                    </div>
                    <button className={style.followButton}>Follow</button>
                </div>
            </section>
            <section>
                <ul>
                    <li>
                        <Post />
                    </li>
                    <li>
                        <Post />
                    </li>
                    <li>
                        <Post />
                    </li>
                </ul>
            </section>
        </div>
    );
};

export default Page;
