import style from './followList.module.css';
import Follow from '@/app/(afterLogin)/_component/Follow';

const FollowList = () => {
    const usersDummyData = [
        {
            id: 'elon',
            name: 'Elon musk',
            image: '',
            Followers: [],
            _count: { Followers: 0, Followings: 0 },
        },
        {
            id: 'mark',
            name: 'Mark Zuckerberg',
            image: '',
            Followers: [],
            _count: { Followers: 0, Followings: 0 },
        },
        {
            id: 'sundar',
            name: 'Sundar Pichai',
            image: '',
            Followers: [],
            _count: { Followers: 0, Followings: 0 },
        },
        {
            id: 'jeffrey',
            name: 'Jeffrey Preston Bezos',
            image: '',
            Followers: [],
            _count: { Followers: 0, Followings: 0 },
        },
    ];
    return (
        <section className={style.container}>
            <h2 className={style.title}>팔로우 추천</h2>
            <ul>
                {usersDummyData.map(user => (
                    <Follow key={user.id} user={user} />
                ))}
            </ul>
        </section>
    );
};

export default FollowList;
