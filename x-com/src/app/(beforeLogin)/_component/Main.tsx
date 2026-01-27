import style from './main.module.css';
import Link from 'next/link';

const Main = () => {
    return (
        <main className={style.container}>
            <h1 className={style.left}>
                <svg
                    viewBox='0 0 24 24'
                    aria-hidden='true'
                    className='r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-lrvibr r-m6rgpd r-1nao33i r-rxcuwo r-1777fci r-m327ed r-494qqr'
                >
                    <g>
                        <path
                            d='M21.742 21.75l-7.563-11.179 7.056-8.321h-2.456l-5.691 6.714-4.54-6.714H2.359l7.29 10.776L2.25 21.75h2.456l6.035-7.118 4.818 7.118h6.191-.008zM7.739 3.818L18.81 20.182h-2.447L5.29 3.818h2.447z'
                            fill='#fff'
                        ></path>
                    </g>
                </svg>
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
