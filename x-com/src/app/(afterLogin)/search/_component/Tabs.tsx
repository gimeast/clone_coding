'use client';

import style from './tabs.module.css';
import { useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';

const Tabs = () => {
    const [postType, setPostType] = useState('hot');
    const searchParams = useSearchParams();
    const router = useRouter();

    const handleHotPost = () => {
        router.replace(`/search?q=${searchParams.get('q')}`);
    };
    const handleNewPost = () => {
        router.replace(`/search?${searchParams.toString()}&f=live`);
    };

    useEffect(() => {
        const type = searchParams.get('f');
        if (type === 'live') setPostType('new');
        else setPostType('hot');
    }, [searchParams]);

    return (
        <div className={style.tabs}>
            <button type='button' onClick={handleHotPost}>
                인기
                <div className={`${postType === 'hot' && style.selected}`}></div>
            </button>
            <button type='button' onClick={handleNewPost}>
                최신
                <div className={`${postType === 'new' && style.selected}`}></div>
            </button>
        </div>
    );
};

export default Tabs;
