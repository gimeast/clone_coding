'use client';

import { X, Image as ImageSvg } from 'lucide-react';
import style from './page.module.css';
import { FormEvent, useCallback, useEffect, useRef, useState } from 'react';
import Image from 'next/image';
import { useRouter } from 'next/navigation';

const Page = () => {
    const [content, setContent] = useState('');
    const router = useRouter();
    const dialogRef = useRef<HTMLDialogElement>(null);

    const disabled = content.trim().length === 0;

    const handleClose = useCallback(() => {
        router.back();
    }, [router]);

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        handleClose();
    };

    useEffect(() => {
        const dialog = dialogRef.current;
        dialog?.showModal();
        dialog?.addEventListener('cancel', handleClose);
        return () => dialog?.removeEventListener('cancel', handleClose);
    }, [handleClose]);

    return (
        <dialog className={style.container} ref={dialogRef}>
            <form className={style.modal} onSubmit={handleSubmit}>
                <button type='button' className={style.close} onClick={handleClose}>
                    <X />
                </button>
                <div className={style.body}>
                    <Image className={style.profile} src='/dummy_profile.webp' alt='프로필' width={50} height={50} />
                    <textarea
                        cols={60}
                        rows={2}
                        placeholder='What’s happening'
                        onChange={e => setContent(e.target.value)}
                    />
                </div>
                <div className={style.bottom}>
                    <ImageSvg />
                    <button type='submit' disabled={disabled}>
                        Post
                    </button>
                </div>
            </form>
        </dialog>
    );
};

export default Page;
