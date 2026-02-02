'use client';

import Home from '@/app/(afterLogin)/home/page';
import { useEffect } from 'react';
import { useRouter } from 'next/navigation';

const Page = () => {
    const router = useRouter();

    useEffect(() => {
        router.replace('/compose/post');
    }, [router]);

    return <Home />;
};

export default Page;
