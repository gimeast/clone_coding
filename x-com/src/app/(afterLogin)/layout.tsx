import style from './layout.module.css';
import Logo from '@/assets/logo.svg';

import { ReactNode } from 'react';
import Link from 'next/link';
import NavMenu from '@/app/(afterLogin)/_component/NavMenu';
import TrendList from '@/app/(afterLogin)/_component/TrendList';
import FollowList from '@/app/(afterLogin)/_component/FollowList';
import RightSearchZone from '@/app/(afterLogin)/_component/RightSearchZone';
import Image from 'next/image';

const Layout = ({ children, modal }: { children: ReactNode; modal: ReactNode }) => {
    return (
        <div className={style.container}>
            <header className={style.leftHeader}>
                <h1 className={style.logo}>
                    <Link href='/home'>
                        <Logo />
                    </Link>
                </h1>
                <NavMenu />
                <button className={style.logoutButton}>
                    <Image className={style.profile} src='/dummy_profile.webp' alt='프로필' width={40} height={40} />
                    <div>
                        <span className={style.userName}>Elon_Musk</span>
                        <span className={style.userId}>@elonmusk</span>
                    </div>
                </button>
            </header>
            <main className={style.main}>
                <div className={style.mainSection}>{children}</div>
                <aside className={style.rightSection}>
                    <RightSearchZone />
                    <div className={style.content}>
                        <TrendList />
                        <FollowList />
                    </div>
                </aside>
            </main>
            {modal}
        </div>
    );
};

export default Layout;
