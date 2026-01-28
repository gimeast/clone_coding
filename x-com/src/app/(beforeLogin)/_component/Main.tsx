import Logo from '@/assets/logo.svg';
import style from './main.module.css';
import Link from 'next/link';

const Main = () => {
    return (
        <main className={style.container}>
            <h1 className={style.left}>
                <Logo />
            </h1>
            <div className={style.right}>
                <div className={style.title}>
                    <span>지금 일어나고 있는 일</span>
                    <span>지금 가입하세요.</span>
                </div>
                <div className={style.buttonWrap}>
                    <Link className={style.signup} href='/i/flow/signup'>
                        계정 만들기
                    </Link>
                    <Link className={style.login} href='login'>
                        로그인
                    </Link>
                </div>
            </div>
        </main>
    );
};

export default Main;
