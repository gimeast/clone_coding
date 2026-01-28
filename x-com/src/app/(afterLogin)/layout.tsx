import style from './layout.module.css';

import { ReactNode } from 'react';

const Layout = ({ children }: { children: ReactNode }) => {
    return (
        <div className={style.container}>
            <header className={style.leftHeader}></header>
            <main className={style.main}>
                <section className={style.mainSection}>{children}</section>
                <section className={style.rightSection}></section>
            </main>
        </div>
    );
};

export default Layout;
