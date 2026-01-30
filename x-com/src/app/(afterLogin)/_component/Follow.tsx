import Logo from '@/assets/logo.svg';
import style from './follow.module.css';
import { User } from '@/model/User';

const Follow = ({ user }: { user: User }) => {
    return (
        <li className={style.follow}>
            <div>
                <div className={style.logo}>
                    <Logo />
                </div>
                <div className={style.userWrapper}>
                    <span className={style.userName}>{user.name}</span>
                    <span className={style.hashtag}>@{user.id}</span>
                </div>
            </div>
            <button type='button' className={style.followButton}>
                팔로우
            </button>
        </li>
    );
};

export default Follow;
