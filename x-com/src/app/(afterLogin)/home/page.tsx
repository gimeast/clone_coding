'use client';

import { Image as ImgButton } from 'lucide-react';
import style from './page.module.css';
import Image from 'next/image';
import { useRef } from 'react';
import Post from '@/app/(afterLogin)/_component/Post';

const Page = () => {
    const fileRef = useRef<HTMLInputElement>(null);

    const handleFile = () => {
        fileRef?.current?.click();
    };
    return (
        <div className={style.container}>
            <nav className={style.tabs}>
                <button>For you</button>
                <button>Following</button>
            </nav>
            <form className={style.formWrapper}>
                <Image className={style.profile} src='/dummy_profile.webp' alt='프로필' width={50} height={50} />
                <div>
                    <textarea cols={50} rows={2} placeholder={"What's happening?"} />
                    <div className={style.buttonWrapper}>
                        <input type='file' ref={fileRef} multiple hidden />
                        <button type='button' className={style.imgButton} onClick={handleFile}>
                            <ImgButton />
                        </button>
                        <button type='submit'>Post</button>
                    </div>
                </div>
            </form>
            <Post />
        </div>
    );
};

export default Page;
