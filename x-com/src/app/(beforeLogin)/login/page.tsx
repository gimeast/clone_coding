'use client';

import { useRouter } from 'next/navigation';
import { useEffect } from 'react';
import Main from '@/app/(beforeLogin)/_component/Main';

const Page = () => {
    const router = useRouter();

    useEffect(() => {
        router.replace('/i/flow/login');
    }, [router]);

    return <Main />;
};

export default Page;
