'use client';

import { X } from 'lucide-react';
import { useRouter } from 'next/navigation';

const BackButton = ({ color }: { color: 'white' | 'black' }) => {
    const router = useRouter();
    const handleClose = () => {
        router.back();
    };

    return (
        <button onClick={handleClose}>
            <X color={color} />
        </button>
    );
};

export default BackButton;
