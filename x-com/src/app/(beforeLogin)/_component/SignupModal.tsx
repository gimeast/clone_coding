'use client';

import { X } from 'lucide-react';
import style from './signup.module.css';
import { ChangeEvent, useState } from 'react';
import { useRouter } from 'next/navigation';

const SignupModal = () => {
    const [name, setName] = useState('');
    const [idType, setIdType] = useState<'phone' | 'email'>('phone');

    const router = useRouter();

    const date = new Date();
    const currentYear = date.getFullYear();
    date.setFullYear(currentYear - 120);

    const month = Array.from({ length: 12 }, (_, i) => i + 1);
    const day = Array.from({ length: 31 }, (_, i) => i + 1);
    const year = Array.from({ length: 121 }, (_, i) => currentYear - i);

    const handleName = (e: ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    };
    const handleIdType = () => {
        setIdType(idType === 'phone' ? 'email' : 'phone');
    };

    return (
        <div className={style.container}>
            <div className={style.headerWrapper}>
                <div className={style.close}>
                    <button onClick={() => router.back()}>
                        <X />
                    </button>
                </div>
                <svg
                    width={32}
                    viewBox='0 0 24 24'
                    aria-label='X'
                    role='img'
                    className='r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-lrvibr r-m6rgpd r-1nao33i r-16y2uox r-lwhw9o'
                >
                    <g>
                        <path
                            d='M21.742 21.75l-7.563-11.179 7.056-8.321h-2.456l-5.691 6.714-4.54-6.714H2.359l7.29 10.776L2.25 21.75h2.456l6.035-7.118 4.818 7.118h6.191-.008zM7.739 3.818L18.81 20.182h-2.447L5.29 3.818h2.447z'
                            fill='#fff'
                        ></path>
                    </g>
                </svg>
                <div></div>
            </div>
            <form className={style.formWrapper}>
                <span className={style.title}>계정을 생성하세요</span>
                <div className={style.textContainer}>
                    <label htmlFor='name'>
                        <span>이름</span>
                        <input type='text' id='name' value={name} onChange={handleName} placeholder='이름' />
                    </label>
                    <label htmlFor='phone'>
                        <span>{idType === 'phone' ? '휴대폰' : '이메일'}</span>
                        <input type='text' id='phone' placeholder={idType === 'phone' ? '휴대폰' : '이메일'} />
                    </label>
                    <button type='button' className={style.typeButton} onClick={handleIdType}>
                        {idType === 'phone' ? '대신 이메일 사용하기' : '대신 휴대폰 사용하기'}
                    </button>
                </div>
                <div className={style.birthdayWrapper}>
                    <span>생년월일</span>
                    <p>
                        이 정보는 공개적으로 표시되지 않습니다. 비즈니스, 반려동물 등 계정 주제에 상관없이 나의 연령을
                        확인하세요.
                    </p>
                    <div className={style.selectWrapper}>
                        <label htmlFor='month'>
                            <select name='month' id='month' defaultValue=''>
                                <option value='' disabled></option>
                                {month.map(v => (
                                    <option key={v} value={v}>
                                        {v}월
                                    </option>
                                ))}
                            </select>
                        </label>
                        <label htmlFor='day'>
                            <select name='day' id='day' defaultValue=''>
                                <option value='' disabled></option>
                                {day.map(v => (
                                    <option key={v} value={v}>
                                        {v}
                                    </option>
                                ))}
                            </select>
                        </label>
                        <label htmlFor='year'>
                            <select name='year' id='year' defaultValue=''>
                                <option value='' disabled></option>
                                {year.map(v => (
                                    <option key={v} value={v}>
                                        {v}
                                    </option>
                                ))}
                            </select>
                        </label>
                    </div>
                </div>
                <button type='submit'>다음</button>
            </form>
        </div>
    );
};

export default SignupModal;
