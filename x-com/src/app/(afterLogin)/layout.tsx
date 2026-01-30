import style from './layout.module.css';
import Logo from '@/assets/logo.svg';

import { ReactNode } from 'react';
import Link from 'next/link';
import NavMenu from '@/app/(afterLogin)/_component/NavMenu';
import { Search } from 'lucide-react';
import SearchInput from '@/app/(afterLogin)/_component/SearchInput';
import TrendList from '@/app/(afterLogin)/_component/TrendList';
import FollowList from '@/app/(afterLogin)/_component/FollowList';

const Layout = ({ children }: { children: ReactNode }) => {
    return (
        <div className={style.container}>
            <header className={style.leftHeader}>
                <h1 className={style.logo}>
                    <Link href='/home'>
                        <Logo />
                    </Link>
                </h1>
                <NavMenu />
                <button className={style.logoutButton}>로그아웃 버튼</button>
            </header>
            <main className={style.main}>
                <section className={style.mainSection}>{children}</section>
                <section className={style.rightSection}>
                    <SearchInput />
                    <div className={style.horizontalLine}></div>
                    <TrendList />
                    <FollowList />
                </section>
            </main>
        </div>
    );
};

export default Layout;
