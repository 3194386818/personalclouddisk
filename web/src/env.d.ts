interface ImportMetaEnv {
    readonly VITE_SERVER_HOST: string
    readonly VITE_SLICE_SIZE: string
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}
