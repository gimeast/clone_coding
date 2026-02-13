'use client';

import style from './postForm.module.css';
import Image from 'next/image';
import { Image as ImgButton } from 'lucide-react';
import { useRef } from 'react';

const PostForm = () => {
    const fileRef = useRef<HTMLInputElement>(null);

    const handleFile = () => {
        fileRef?.current?.click();
    };

    return (
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
    );
};

export default PostForm;
