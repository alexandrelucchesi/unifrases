CREATE TABLE tb_sentence(
  id bigserial PRIMARY KEY,
  code text,
  pt_br text,
  en_us text
);

CREATE TABLE tb_language(
  locale text PRIMARY KEY,
  pt_br text,
  en_us text
);
