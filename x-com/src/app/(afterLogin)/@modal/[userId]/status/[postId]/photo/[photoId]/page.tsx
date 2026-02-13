'use client';

import style from './page.module.css';
import { X } from 'lucide-react';
import { useCallback, useEffect, useRef } from 'react';
import { useRouter } from 'next/navigation';
import Image from 'next/image';
import { faker } from '@faker-js/faker/locale/ko';
import Post from '@/app/(afterLogin)/_component/Post';

const Page = () => {
    const router = useRouter();
    const dialogRef = useRef<HTMLDialogElement>(null);

    const photo = {
        imageId: 1,
        link: faker.image.urlPicsumPhotos(),
        Post: {
            content: faker.lorem.text(),
        },
    };

    const handleClose = useCallback(() => {
        router.back();
    }, [router]);

    useEffect(() => {
        const dialog = dialogRef.current;
        dialog?.showModal();
    }, [dialogRef]);

    return (
        <dialog className={style.container} ref={dialogRef}>
            <div className={style.imageZone}>
                <button type='button' className={style.close} onClick={handleClose}>
                    <X />
                </button>
                <Image src={photo.link} alt='이미지' fill />
            </div>
            <div className={style.commentZone}>
                <Post noImage />
            </div>
        </dialog>
    );
};

export default Page;
