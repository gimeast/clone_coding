'use client';

import style from './navMenu.module.css';
import Link from 'next/link';
import { useSelectedLayoutSegment } from 'next/navigation';
import { House, Mail, Search, UserRound } from 'lucide-react';

const NavMenu = () => {
    const segment = useSelectedLayoutSegment();
    const userId = 'Elon_Musk';

    return (
        <nav className={style.nav}>
            <ul>
                <li className={segment === 'home' ? style.active : ''}>
                    <Link href='/home'>
                        <House />
                        <span>Home</span>
                    </Link>
                </li>
                <li className={segment === 'explore' || segment === 'search' ? style.active : ''}>
                    <Link href='/explore'>
                        <Search />
                        <span>Explore</span>
                    </Link>
                </li>
                <li className={segment === 'chat' ? style.active : ''}>
                    <Link href='/messages'>
                        <Mail />
                        <span>Message</span>
                    </Link>
                </li>
                <li className={segment === 'profile' ? style.active : ''}>
                    <Link href={`/${userId}`}>
                        <UserRound />
                        <span>Profile</span>
                    </Link>
                </li>
            </ul>
            <Link className={style.postButton} href='/compose/post'>
                <span>Post</span>
                <svg
                    viewBox='0 0 24 24'
                    aria-hidden='true'
                    className='r-jwli3a r-4qtqp9 r-yyyyoo r-1472mwg r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-lrsllp'
                >
                    <g>
                        <path d='M23 3c-6.62-.1-10.38 2.421-13.05 6.03C7.29 12.61 6 17.331 6 22h2c0-1.007.07-2.012.19-3H12c4.1 0 7.48-3.082 7.94-7.054C22.79 10.147 23.17 6.359 23 3zm-7 8h-1.5v2H16c.63-.016 1.2-.08 1.72-.188C16.95 15.24 14.68 17 12 17H8.55c.57-2.512 1.57-4.851 3-6.78 2.16-2.912 5.29-4.911 9.45-5.187C20.95 8.079 19.9 11 16 11zM4 9V6H1V4h3V1h2v3h3v2H6v3H4z'></path>
                    </g>
                </svg>
            </Link>
        </nav>
    );
};

export default NavMenu;
