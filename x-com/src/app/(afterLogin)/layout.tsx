import style from './layout.module.css';
import Logo from '@/assets/logo.svg';
import Search from '@/assets/search.svg';

import { ReactNode } from 'react';
import Link from 'next/link';

const Layout = ({ children }: { children: ReactNode }) => {
    return (
        <div className={style.container}>
            <header className={style.leftHeader}>
                <h1 className={style.logo}>
                    <Link href='/home'>
                        <Logo />
                    </Link>
                </h1>
            </header>
            <main className={style.main}>
                <section className={style.mainSection}>{children}</section>
                <section className={style.rightSection}>
                    <div className={style.searchWrapper}>
                        <div className={style.searchBorder}>
                            <Search />
                            <label className='sr-only' htmlFor='search'>
                                검색
                            </label>
                            <input type='text' id='search' />
                        </div>
                    </div>
                </section>
            </main>
        </div>
    );
};

export default Layout;
