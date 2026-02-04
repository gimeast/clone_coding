'use client';

import style from './navMenu.module.css';
import Link from 'next/link';
import { useSelectedLayoutSegment } from 'next/navigation';
import { House, Mail, Search, UserRound } from 'lucide-react';

const NavMenu = () => {
    const segment = useSelectedLayoutSegment();

    return (
        <nav className={style.nav}>
            <ul>
                <li className={segment === 'home' ? style.active : ''}>
                    <Link href='/home'>
                        <House />
                        Home
                    </Link>
                </li>
                <li className={segment === 'explore' || segment === 'search' ? style.active : ''}>
                    <Link href='/explore'>
                        <Search />
                        Explore
                    </Link>
                </li>
                <li className={segment === 'chat' ? style.active : ''}>
                    <Link href='/message'>
                        <Mail />
                        Message
                    </Link>
                </li>
                <li className={segment === 'profile' ? style.active : ''}>
                    <Link href='/profile'>
                        <UserRound />
                        Profile
                    </Link>
                </li>
            </ul>
            <Link className={style.postButton} href='/compose/post'>
                Post
            </Link>
        </nav>
    );
};

export default NavMenu;
